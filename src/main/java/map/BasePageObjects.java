package map;

import org.openqa.selenium.WebElement;
import support.GetElements;
import support.annotations.FindBy;
import support.enums.LocatorType;

import java.lang.reflect.Field;

import static org.openqa.selenium.By.*;
import static support.enums.LocatorType.*;

public class BasePageObjects {
    private LocatorType locType;
    private final GetElements gE = new GetElements();

    protected WebElement setElement(String fieldName) {
        setLocatorType(fieldName);
        return findBy(locType, fieldName);
    }

    private void setLocatorType(String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {

            if (isTheFieldAnnotated(field, fieldName)) {
                final FindBy findBy = field.getAnnotation(FindBy.class);

                if (findBy.id().length() != 0) {
                    assertEmpty(new String[]{findBy.xPath(), findBy.cssSelector()});
                    locType = ID;

                } else if (findBy.xPath().length() != 0) {
                    assertEmpty(new String[]{findBy.id(), findBy.cssSelector()});
                    locType = X_PATH;

                } else if (findBy.cssSelector().length() != 0) {
                    assertEmpty(new String[]{findBy.xPath(), findBy.id()});
                    locType = CSS_SELECTOR;

                } else
                    throw new RuntimeException("Por favor informe um enum LocatorType válido.");
            }
        }
    }

    private void assertEmpty(String[] args) {
        for (String arg : args)
            if (!arg.intern().equals(""))
                throw new RuntimeException("Por favor informe um localizador único [id, xPath ou cssSelector] por campo");
    }

    private WebElement findBy(LocatorType locType, String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isTheFieldAnnotated(field, fieldName)) {
                final FindBy findBy = field.getAnnotation(FindBy.class);

                if (locType.equals(X_PATH))
                    return gE.findElement(xpath(findBy.xPath()));

                if (locType.equals(CSS_SELECTOR))
                    return gE.findElement(cssSelector(findBy.cssSelector()));

                if (locType.equals(ID))
                    return gE.findElement(id(findBy.id()));
            }
        }
        return null;
    }

    private boolean isTheFieldAnnotated(Field field, String fieldName) {
        final boolean isTheField = field.getName().intern().equals(fieldName);
        final boolean isAnnotated = field.isAnnotationPresent(FindBy.class);

        return isTheField && isAnnotated;
    }
}
