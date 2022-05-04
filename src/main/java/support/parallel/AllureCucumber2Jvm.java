package support.parallel;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.*;

import java.io.InputStream;
import java.util.Optional;
import java.util.Properties;
import java.util.function.Consumer;

public class AllureCucumber2Jvm extends io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm {

    public static ThreadLocal<Properties> propriedadesAppConfig = new ThreadLocal<Properties>() {
    };

    public AllureCucumber2Jvm() {
        super(new AllureLifecycle() {
            private AllureLifecycle lifecycle = Allure.getLifecycle();

            public int hashCode() {
                return lifecycle.hashCode();
            }

            private String getSufixo() {
                try {
                    Properties appConfig = propriedadesAppConfig.get();
                    if (appConfig == null) {
                        appConfig = new Properties();
                        InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("web/app.config");
                        appConfig.load(is);
                        propriedadesAppConfig.set(appConfig);
                        is.close();
                    }
                    return " (" +
                            appConfig.get("browser.mobile.device.name") +
                            " / " +
                            appConfig.get("browser") +
                            ")";
                } catch (Throwable e) {
                    e.printStackTrace(System.out);
                    return "";
                }
            }

            public void startTestContainer(String containerUuid, TestResultContainer container) {
                // container.setName(container.getName() + getSufixo());
                lifecycle.startTestContainer(containerUuid, container);
            }

            public boolean equals(Object obj) {
                return lifecycle.equals(obj);
            }

            public void startTestContainer(TestResultContainer container) {
                // container.setName(container.getName() + getSufixo());
                lifecycle.startTestContainer(container);
            }

            public void updateTestContainer(String uuid, Consumer<TestResultContainer> update) {
                lifecycle.updateTestContainer(uuid, update);
            }

            public void stopTestContainer(String uuid) {
                lifecycle.stopTestContainer(uuid);
            }

            public void writeTestContainer(String uuid) {
                lifecycle.writeTestContainer(uuid);
            }

            public void startPrepareFixture(String containerUuid, String uuid, FixtureResult result) {
                lifecycle.startPrepareFixture(containerUuid, uuid, result);
            }

            public void startTearDownFixture(String containerUuid, String uuid, FixtureResult result) {
                lifecycle.startTearDownFixture(containerUuid, uuid, result);
            }

            public void updateFixture(Consumer<FixtureResult> update) {
                lifecycle.updateFixture(update);
            }

            public void updateFixture(String uuid, Consumer<FixtureResult> update) {
                lifecycle.updateFixture(uuid, update);
            }

            public void stopFixture(String uuid) {
                lifecycle.stopFixture(uuid);
            }

            public String toString() {
                return lifecycle.toString();
            }

            public Optional<String> getCurrentTestCase() {
                return lifecycle.getCurrentTestCase();
            }

            public Optional<String> getCurrentTestCaseOrStep() {
                return lifecycle.getCurrentTestCaseOrStep();
            }

            public boolean setCurrentTestCase(String uuid) {
                return lifecycle.setCurrentTestCase(uuid);
            }

            public void scheduleTestCase(String containerUuid, TestResult result) {
                String nomeAntigo = null;
                String novoNome = null;
                for (int i = 0; i < result.getLabels().size(); i++) {
                    Label label = result.getLabels().get(i);
                    if ("feature".equals(label.getName()) ||
                            "package".equals(label.getName()) ||
                            "suite".equals(label.getName())) {
                        nomeAntigo = label.getValue();
                        novoNome = label.getValue() + getSufixo();
                        label.setValue(label.getValue() + getSufixo());
                    }
                }
                if (nomeAntigo != null && novoNome != null && result.getFullName() != null) {
                    result.setFullName(result.getFullName().replace(nomeAntigo, novoNome));
                }
                result.setHistoryId(Integer.toString(result.getFullName().hashCode()));
                // result.setName(result.getName() + "scheduleTestCase" + getSufixo());
                lifecycle.scheduleTestCase(containerUuid, result);
            }

            public void scheduleTestCase(TestResult result) {
                String nomeAntigo = null;
                String novoNome = null;
                for (int i = 0; i < result.getLabels().size(); i++) {
                    Label label = result.getLabels().get(i);
                    if ("feature".equals(label.getName()) ||
                            "package".equals(label.getName()) ||
                            "suite".equals(label.getName())) {
                        nomeAntigo = label.getValue();
                        novoNome = label.getValue() + getSufixo();
                        label.setValue(label.getValue() + getSufixo());
                    }
                }
                if (nomeAntigo != null && novoNome != null && result.getFullName() != null) {
                    result.setFullName(result.getFullName().replace(nomeAntigo, novoNome));
                }
                result.setHistoryId(Integer.toString(result.getFullName().hashCode()));
                // result.setName(result.getName() + "scheduleTestCase" + getSufixo());
                lifecycle.scheduleTestCase(result);
            }

            public void startTestCase(String uuid) {
                lifecycle.startTestCase(uuid);
            }

            public void updateTestCase(Consumer<TestResult> update) {
                lifecycle.updateTestCase(update);
            }

            public void updateTestCase(String uuid, Consumer<TestResult> update) {
                lifecycle.updateTestCase(uuid, update);
            }

            public void stopTestCase(String uuid) {
                lifecycle.stopTestCase(uuid);
            }

            public void writeTestCase(String uuid) {
                lifecycle.writeTestCase(uuid);
            }

            public void startStep(String uuid, StepResult result) {
                lifecycle.startStep(uuid, result);
            }

            public void startStep(String parentUuid, String uuid, StepResult result) {
                lifecycle.startStep(parentUuid, uuid, result);
            }

            public void updateStep(Consumer<StepResult> update) {
                lifecycle.updateStep(update);
            }

            public void updateStep(String uuid, Consumer<StepResult> update) {
                lifecycle.updateStep(uuid, update);
            }

            public void stopStep() {
                lifecycle.stopStep();
            }

            public void stopStep(String uuid) {
                lifecycle.stopStep(uuid);
            }

            public void addAttachment(String name, String type, String fileExtension, byte[] body) {
                lifecycle.addAttachment(name, type, fileExtension, body);
            }

            public void addAttachment(String name, String type, String fileExtension, InputStream stream) {
                lifecycle.addAttachment(name, type, fileExtension, stream);
            }

            public String prepareAttachment(String name, String type, String fileExtension) {
                return lifecycle.prepareAttachment(name, type, fileExtension);
            }

            public void writeAttachment(String attachmentSource, InputStream stream) {
                lifecycle.writeAttachment(attachmentSource, stream);
            }

        });
    }

}
