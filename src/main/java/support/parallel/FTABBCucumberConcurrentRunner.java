package support.parallel;

import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.jar.Manifest;

public class FTABBCucumberConcurrentRunner extends Runner {
    private final Description description;
    private final List<ExecutorService> listaExecutor = new Vector<>();
    private final List<String> listaAppConfig;
    private final Class<?>[] listaClassesTesteParaExecucao;
    private final List<ExecucaoPendente> listaExecucoesPendentes = new Vector<>();
    private final Map<Integer, Description> mapaDescriptionPorSequencialExecucaoTeste = new ConcurrentHashMap<>();
    private Method beforeClass;
    private Method afterClass;
    private final List<CompletableFuture<Void>> listaFutures = new ArrayList<>();
    private int novasTentativasAposFalharTeste;

    public FTABBCucumberConcurrentRunner(Class<?> runner) throws InitializationError, IOException {
        super();
        if (runner == null) {
            throw new RuntimeException("Informe a classe runner para executar esta suite concorrente de testes");
        }
        SuiteClasses suiteClassesAnnotation = runner.getAnnotation(SuiteClasses.class);
        if (suiteClassesAnnotation == null || suiteClassesAnnotation.value() == null || suiteClassesAnnotation.value().length == 0) {
            throw new RuntimeException("A anotação " +
                    SuiteClasses.class.getCanonicalName() +
                    " não consta ou não está definida corretamente na classe '" +
                    runner.getCanonicalName() +
                    "'.\nEspecifique as classes a serem executadas usando esta anotação.\nExemplo de uso: @SuiteClasses({ Runner1.class, Runner2.class, Runner3.class })");
        }
        AppConfig appConfigAnnotation = runner.getAnnotation(AppConfig.class);
        if (appConfigAnnotation == null || appConfigAnnotation.value() == null || appConfigAnnotation.value().length == 0) {
            throw new RuntimeException("A anotação " +
                    AppConfig.class.getCanonicalName() +
                    " não consta ou não está definida corretamente na classe '" +
                    runner.getCanonicalName() +
                    "'.\nExemplo de uso: @AppConfig({ \"emulator-5554\", \"emulator-5556\", \"emulator-5558\" })\n\nou\n\n@AppConfig({ \"\" }) (usa a configuração padrão para os testes)");
        }
        this.novasTentativasAposFalharTeste = appConfigAnnotation.novasTentativasAposFalharTeste();
        if (this.novasTentativasAposFalharTeste < 0) {
            System.out.println("Observação: novasTentativasAposFalharTeste não pode ser menor que 0. Assumindo o valor 0.");
            this.novasTentativasAposFalharTeste = 0;
        }
        if (this.novasTentativasAposFalharTeste > 4) {
            System.out.println("Observação: novasTentativasAposFalharTeste não pode ser maior que 4. Assumindo o valor 4.");
            this.novasTentativasAposFalharTeste = 4;
        }
        this.listaAppConfig = new Vector<>();
        Collections.addAll(this.listaAppConfig, appConfigAnnotation.value());
        this.description = Description.createSuiteDescription(runner);
        int sequencialTeste = 1;
        for (Class<?> classe : suiteClassesAnnotation.value()) {
            Description description = new Cucumber(classe).getDescription();
            description = duplicar(description);
            this.mapaDescriptionPorSequencialExecucaoTeste.put(sequencialTeste, description);
            this.description.addChild(description);
            sequencialTeste++;
        }
        this.listaClassesTesteParaExecucao = suiteClassesAnnotation.value();
        Method[] metodos = runner.getDeclaredMethods();
        for (Method metodo : metodos) {
            BeforeClass beforeClassAnnotation = metodo.getAnnotation(BeforeClass.class);
            if (beforeClassAnnotation != null) {
                if (!Modifier.isStatic(metodo.getModifiers())) {
                    throw new RuntimeException("Método " + metodo.getName() + " deve ser static");
                }
                if (metodo.getParameterCount() != 0) {
                    throw new RuntimeException("Método " + metodo.getName() + " não deve possuir parâmetros");
                }
                this.beforeClass = metodo;
            }
            AfterClass afterClassAnnotation = metodo.getAnnotation(AfterClass.class);
            if (afterClassAnnotation != null) {
                if (!Modifier.isStatic(metodo.getModifiers())) {
                    throw new RuntimeException("Método " + metodo.getName() + " deveria ser static");
                }
                if (metodo.getParameterCount() != 0) {
                    throw new RuntimeException("Método " + metodo.getName() + " não deve possuir parâmetros");
                }
                this.afterClass = metodo;
            }
        }
    }

    private Description duplicar(Description description) {
        Description novoDescription;
        if (description.isTest()) {
            novoDescription = Description.createTestDescription(
                    description.getClassName(),
                    description.getDisplayName(),
                    new Serializable() {
                        private static final long serialVersionUID = 1L;
                    });
        } else {
            novoDescription = Description.createSuiteDescription(
                    description.getClassName(),
                    new Serializable() {
                        private static final long serialVersionUID = 1L;
                    });
        }
        for (Description teste : description.getChildren()) {
            novoDescription.addChild(duplicar(teste));
        }
        return novoDescription;
    }

    @Override
    public Description getDescription() {
        return description;
    }

    private static class ExecucaoPendente {
        public int sequencialExecucaoTeste;
        public int sequencialTeste;
        public Class<?> classeTeste;

        public ExecucaoPendente(int sequencialExecucaoTeste, int sequencialTeste, Class<?> classeTeste) {
            super();
            this.sequencialExecucaoTeste = sequencialExecucaoTeste;
            this.sequencialTeste = sequencialTeste;
            this.classeTeste = classeTeste;
        }
    }

    @Override
    public void run(RunNotifier notifier) {
        long tempoInicio = System.currentTimeMillis();
        try {
            System.out.println("Quantidade de testes a serem executados: " + this.listaClassesTesteParaExecucao.length);
            System.out.println("Quantidade de execuções simultâneas: " + this.listaAppConfig.size());
            System.out.println("Quantidade máxima de reexecuções de cada teste após falhas: " + novasTentativasAposFalharTeste);
            System.out.println("Configurações disponíveis para execução (app.config): " + listToString(this.listaAppConfig));

            if (beforeClass != null) {
                beforeClass.invoke(null);
            }

            ClassLoader classLoaderBase = Thread.currentThread().getContextClassLoader();

            int sequencialTeste = 1;
            for (Class<?> classeTeste : this.listaClassesTesteParaExecucao) {
                listaExecucoesPendentes.add(new ExecucaoPendente(1, sequencialTeste, classeTeste));
                sequencialTeste++;
            }

            for (String appConfig : this.listaAppConfig) {
                ExecutorService executor = Executors.newFixedThreadPool(1);
                this.listaExecutor.add(executor);
                executarProximaExecucaoPendente(appConfig, notifier, classLoaderBase, executor);
            }

            for (CompletableFuture<Void> future : this.listaFutures) {
                future.get();
            }

            if (afterClass != null) {
                afterClass.invoke(null);
            }
        } catch (Throwable e) {
            e.printStackTrace(System.out);
        } finally {
            finalizarExecutors();
        }
        long tempoFim = System.currentTimeMillis();
        System.out.println("Fim dos testes. Tempo total de execução: " + formatarMilissegundos(tempoFim - tempoInicio));
    }

    private void finalizarExecutors() {
        for (ExecutorService executor : this.listaExecutor) {
            executor.shutdown();
        }
    }

    private void executarProximaExecucaoPendente(String pathPrefix, RunNotifier notifier, ClassLoader classLoaderBase, ExecutorService executor) {
        try {
            ExecucaoPendente execucaoPendente = listaExecucoesPendentes.remove(0);
            CompletableFuture<Void> f1 = executarTeste(pathPrefix, execucaoPendente.sequencialExecucaoTeste, execucaoPendente.sequencialTeste, notifier, execucaoPendente.classeTeste, classLoaderBase, executor);
            this.listaFutures.add(f1);
        } catch (Throwable e) {
        }
    }

    private String formatarMilissegundos(long milissegundos) {
        long UM_MILISSEGUNDO = 1;
        long UM_SEGUNDO = UM_MILISSEGUNDO * 1000;
        long UM_MINUTO = UM_SEGUNDO * 60;
        long UMA_HORA = UM_MINUTO * 60;
        long UM_DIA = UMA_HORA * 24;

        long dias = (milissegundos - milissegundos % UM_DIA) / UM_DIA;
        milissegundos = milissegundos % UM_DIA;

        long horas = (milissegundos - milissegundos % UMA_HORA) / UMA_HORA;
        milissegundos = milissegundos % UMA_HORA;

        long minutos = (milissegundos - milissegundos % UM_MINUTO) / UM_MINUTO;
        milissegundos = milissegundos % UM_MINUTO;

        long segundos = (milissegundos - milissegundos % UM_SEGUNDO) / UM_SEGUNDO;
        milissegundos = milissegundos % UM_SEGUNDO;

        StringBuffer sb = new StringBuffer();
        if (dias > 0) {
            sb.append(dias).append(" dia(s)");
        }
        if (horas > 0 || sb.length() > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(horas).append(" hora(s)");
        }
        if (minutos > 0 || sb.length() > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(minutos).append(" minuto(s)");
        }
        if (segundos > 0 || sb.length() > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(segundos).append(" segundo(s)");
        }
        if (milissegundos > 0 || sb.length() > 0) {
            if (sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(milissegundos).append(" milissegundo(s)");
        }
        return sb.toString();
    }

    public static String listToString(List<String> a) {
        if (a == null) {
            return "null";
        }

        int iMax = a.size() - 1;
        if (iMax == -1) {
            return "[]";
        }

        StringBuilder b = new StringBuilder();
        b.append('[');
        for (int i = 0; ; i++) {
            b.append('\'');
            String txt = String.valueOf(a.get(i));
            if (txt.isEmpty()) {
                txt = "<<configuração padrão>>";
            }
            b.append(txt);
            b.append('\'');
            if (i == iMax) {
                return b.append(']').toString();
            }
            b.append(", ");
        }
    }

    public static void main(String[] args) throws InitializationError, IOException {
        new FTABBCucumberConcurrentRunner(null).run(new RunNotifier());
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Inherited
    public @interface AppConfig {
        String[] value();

        int novasTentativasAposFalharTeste() default 0;
    }

    private CompletableFuture<Void> executarTeste(String pathPrefix, int sequencialExecucaoTeste, int sequencialTeste, RunNotifier notifier, Class<?> classeTeste, ClassLoader classLoaderBase, ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Description descricaoDoTeste = mapaDescriptionPorSequencialExecucaoTeste.get(sequencialTeste);
                if (sequencialExecucaoTeste == 1) {
                    iniciarTeste(notifier, descricaoDoTeste);
                }
                System.out.println(
                        "Início do teste " + classeTeste.getCanonicalName() +
                                " usando a configuração '" +
                                (pathPrefix == null || pathPrefix.trim().isEmpty() ? "<<configuração padrão>>" : pathPrefix) +
                                "'. (Tentativa " +
                                sequencialExecucaoTeste +
                                " de " +
                                (novasTentativasAposFalharTeste + 1) +
                                ").");
                ProxyClassLoader _proxyClassLoader = criarProxyClassLoader(pathPrefix, classLoaderBase);
                Thread.currentThread().setContextClassLoader(_proxyClassLoader);
                Class<?> _classeTeste = _proxyClassLoader.loadClass(classeTeste.getCanonicalName());
                Class<?> _classeJUnit = _proxyClassLoader.loadClass(JUnitCore.class.getCanonicalName());
                Method _metodoRunClasses = _classeJUnit.getDeclaredMethod("runClasses", Class[].class);
                Object _resultado = _metodoRunClasses.invoke(null, (Object) new Class[]{_classeTeste});

                boolean wasSuccessful = (boolean) _resultado.getClass()
                        .getDeclaredMethod("wasSuccessful", new Class[0])
                        .invoke(_resultado, new Object[0]);

                if (wasSuccessful) {
                    finalizarTeste(notifier, descricaoDoTeste);
                    executarProximaExecucaoPendente(pathPrefix, notifier, classLoaderBase, executor);
                } else {
                    @SuppressWarnings("unchecked")
                    List<Object> failures = (List<Object>) _resultado.getClass()
                            .getDeclaredMethod("getFailures", new Class[0])
                            .invoke(_resultado, new Object[0]);

                    List<Throwable> listaErros = new ArrayList<Throwable>();
                    for (Object failure : failures) {
                        Throwable exception = (Throwable) failure.getClass()
                                .getDeclaredMethod("getException", new Class[0])
                                .invoke(failure, new Object[0]);
                        listaErros.add(exception);
                    }

                    if (sequencialExecucaoTeste <= novasTentativasAposFalharTeste) {
                        synchronized (System.out) {
                            System.out.println(
                                    "Falha do teste " + classeTeste.getCanonicalName() +
                                            " usando a configuração '" +
                                            (pathPrefix == null || pathPrefix.trim().isEmpty() ? "<<configuração padrão>>" : pathPrefix) +
                                            "'. Será tentado novamente a execução deste mesmo teste. Detalhes do erro:");
                            for (Throwable erro : listaErros) {
                                erro.printStackTrace(System.out);
                            }
                        }
                        CompletableFuture<Void> f1 = executarTeste(pathPrefix, sequencialExecucaoTeste + 1, sequencialTeste, notifier, classeTeste, classLoaderBase, executor);
                        listaFutures.add(f1);
                        // ignorarTeste(notifier, descricaoDoTeste);
                    } else {
                        synchronized (System.out) {
                            System.out.println(
                                    "Falha do teste " + classeTeste.getCanonicalName() +
                                            " usando a configuração '" +
                                            (pathPrefix == null || pathPrefix.trim().isEmpty() ? "<<configuração padrão>>" : pathPrefix) +
                                            "'. As tentativas de reexecução deste teste foram ESGOTADAS. Não será tentado novamente a execução deste. Detalhes do erro:");
                            for (Throwable erro : listaErros) {
                                erro.printStackTrace(System.out);
                            }
                        }
                        finalizarTesteComErro(notifier, descricaoDoTeste, listaErros);
                        executarProximaExecucaoPendente(pathPrefix, notifier, classLoaderBase, executor);
                    }
                }

                return null;
            } catch (Throwable e) {
                e.printStackTrace(System.out);
                throw new RuntimeException(e);
            } finally {
                System.out.println("Fim do teste " +
                        classeTeste.getCanonicalName() +
                        " usando a configuração '" +
                        (pathPrefix == null || pathPrefix.trim().isEmpty() ? "<<configuração padrão>>" : pathPrefix) +
                        "'");
            }
        }, executor);
    }

    private void iniciarTeste(RunNotifier notifier, Description descricao) {
        if (descricao == null) {
            return;
        }
        if (descricao.getChildren() == null || descricao.getChildren().size() == 0) {
            notifier.fireTestStarted(descricao);
        }
        for (Description desc : descricao.getChildren()) {
            iniciarTeste(notifier, desc);
        }
    }

    private void finalizarTeste(RunNotifier notifier, Description descricao) {
        if (descricao == null) {
            return;
        }
        if (descricao.getChildren() == null || descricao.getChildren().size() == 0) {
            notifier.fireTestFinished(descricao);
        }
        for (Description desc : descricao.getChildren()) {
            finalizarTeste(notifier, desc);
        }
    }

    private void finalizarTesteComErro(RunNotifier notifier, Description descricao, List<Throwable> erros) {
        if (descricao == null) {
            return;
        }
        if (descricao.getChildren() == null || descricao.getChildren().size() == 0) {
            for (Throwable erro : erros) {
                notifier.fireTestFailure(new Failure(descricao, erro));
            }
        }
        for (Description desc : descricao.getChildren()) {
            finalizarTesteComErro(notifier, desc, erros);
        }
    }

    private void ignorarTeste(RunNotifier notifier, Description descricao) {
        if (descricao == null) {
            return;
        }
        if (descricao.getChildren() == null || descricao.getChildren().size() == 0) {
            notifier.fireTestIgnored(descricao);
        }
        for (Description desc : descricao.getChildren()) {
            ignorarTeste(notifier, desc);
        }
    }

    private static ProxyClassLoader criarProxyClassLoader(String pathPrefix, ClassLoader originalClassLoader) {
        if (originalClassLoader == null) {
            return null;
        } else {
            final ClassLoader originalParentClassLoader;
            if (originalClassLoader == ClassLoader.getSystemClassLoader()) {
                originalParentClassLoader = originalClassLoader.getParent();
            } else {
                originalParentClassLoader = criarProxyClassLoader(pathPrefix, originalClassLoader.getParent());
            }
            return new ProxyClassLoader(pathPrefix, originalClassLoader, originalParentClassLoader);
        }
    }

    private static class ProxyClassLoader extends URLClassLoader {
        private final String pathPrefix;
        private final ClassLoader originalClassLoader;

        public ProxyClassLoader(String pathPrefix, ClassLoader originalClassLoader, ClassLoader parent) {
            super(new URL[0], parent);
            if (originalClassLoader == null) {
                throw new NullPointerException();
            }
            if (pathPrefix == null) {
                throw new NullPointerException();
            }
            if (pathPrefix.trim().isEmpty()) {
                this.pathPrefix = "";
            } else if (!pathPrefix.endsWith("/")) {
                this.pathPrefix = pathPrefix.concat("/");
            } else {
                this.pathPrefix = pathPrefix;
            }
            this.originalClassLoader = originalClassLoader;
        }

        @Override
        protected Class<?> findClass(String name)
                throws ClassNotFoundException {
            Class<?> clazz = this.originalClassLoader.loadClass(name);
            CodeSource cs = null;
            if (clazz.getProtectionDomain() != null &&
                    clazz.getProtectionDomain().getCodeSource() != null) {
                CodeSource originalCodeSource = clazz.getProtectionDomain().getCodeSource();
                URL originalUrl = originalCodeSource.getLocation();
                CodeSigner[] originalSigners = originalCodeSource.getCodeSigners();
                cs = new CodeSource(originalUrl, originalSigners);
            }

            InputStream is = this.originalClassLoader.getResourceAsStream(name.replace('.', '/').concat(".class"));
            if (is == null) {
                throw new ClassNotFoundException(name);
            }
            try {
                int i = name.lastIndexOf('.');
                if (i != -1) {
                    try {
                        CodeSource originalCodeSource = clazz.getProtectionDomain().getCodeSource();
                        URL url = originalCodeSource.getLocation();
                        String pkgname = name.substring(0, i);
                        Manifest man = new Manifest();
                        definePackage(pkgname, man, url);
                    } catch (Throwable e) {
// ignorar quando o package já foi definido e der erro por este motivo
                    }
                }
                byte[] b = new byte[8192];
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = is.read(b);
                while (len != -1) {
                    baos.write(b, 0, len);
                    len = is.read(b);
                }
                b = baos.toByteArray();
                return defineClass(name, b, 0, b.length, cs);
            } catch (IOException e) {
                throw new ClassNotFoundException(name);
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                    }
                }
            }
        }

        @Override
        public URL findResource(String name) {
            URL url = this.originalClassLoader.getResource(pathPrefix + name);
            if (url == null) {
                url = this.originalClassLoader.getResource(name);
            }
            return url;
        }

        @Override
        public Enumeration<URL> findResources(String name)
                throws IOException {
            Enumeration<URL> urls = this.originalClassLoader.getResources(pathPrefix + name);
            if (urls == null || !urls.hasMoreElements()) {
                urls = this.originalClassLoader.getResources(name);
            }
            return urls;
        }

    }

}
