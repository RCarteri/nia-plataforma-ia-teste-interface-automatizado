package map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.GetElements;
import support.annotations.FindBy;
import support.enums.LocatorType;

import java.lang.reflect.Field;

import static support.enums.LocatorType.CSS_SELECTOR;

public class BasePageObjects {
    private final GetElements gE = new GetElements();
    private LocatorType locType;

    protected WebElement setElement(String fieldName) {
        setLocatorType(fieldName);
        return findBy(this.locType, fieldName);
    }

    private void setLocatorType(String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isTheFieldAnnotated(field, fieldName)) {
                this.locType = CSS_SELECTOR;
            }
        }
    }

    private WebElement findBy(LocatorType locType, String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isTheFieldAnnotated(field, fieldName)) {
                final String selector = field.getAnnotation(FindBy.class).cssSelector();
                return gE.findElement(By.cssSelector(selector));
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
