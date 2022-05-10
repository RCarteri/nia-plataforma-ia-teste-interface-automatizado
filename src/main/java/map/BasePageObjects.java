package map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

    private @Nullable WebElement findBy(@NotNull LocatorType locType, String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {

            if (isTheFieldAnnotated(field, fieldName)) {
                final FindBy findBy = field.getAnnotation(FindBy.class);
                if (locType.equals(CSS_SELECTOR))
                    return gE.findElement(By.cssSelector(findBy.cssSelector()));
            }
        }
        return null;
    }

    private boolean isTheFieldAnnotated(@NotNull Field field, String fieldName) {
        final boolean isTheField = field.getName().intern().equals(fieldName);
        final boolean isAnnotated = field.isAnnotationPresent(FindBy.class);

        return isTheField && isAnnotated;
    }
}
