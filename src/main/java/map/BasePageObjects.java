package map;

import org.openqa.selenium.WebElement;
import support.GetElements;
import support.annotations.FindBy;

import java.lang.reflect.Field;

import static org.openqa.selenium.By.cssSelector;

public class BasePageObjects {
    private final GetElements gE = new GetElements();

    protected WebElement setElement(String fieldName) {
        return findBy(fieldName);
    }

    private WebElement findBy(String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isTheFieldAnnotated(field, fieldName)) {
                final String selector = field.getAnnotation(FindBy.class).cssSelector();
                return gE.findElement(cssSelector(selector));
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
