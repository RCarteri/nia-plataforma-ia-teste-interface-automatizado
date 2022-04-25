package map;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.GetElements;
import support.annotations.FindBy;
import support.enums.LocatorType;

import java.lang.reflect.Field;

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
                final FindBy findBy = field.getAnnotation(FindBy.class);
                if (findBy.id().length() != 0) {
                    assertEmpty(new String[]{findBy.xPath(), findBy.cssSelector()});
                    this.locType = LocatorType.ID;
                } else if (findBy.xPath().length() != 0) {
                    assertEmpty(new String[]{findBy.id(), findBy.cssSelector()});
                    this.locType = LocatorType.X_PATH;
                } else if (findBy.cssSelector().length() != 0) {
                    assertEmpty(new String[]{findBy.xPath(), findBy.id()});
                    this.locType = LocatorType.CSS_SELECTOR;
                } else throw new RuntimeException("Por favor informe um [ LocatorTypes ] enum válido");
            }
        }
    }

    private void assertEmpty(String @NotNull [] args) {
        for (String arg : args)
            if (!arg.intern().equals(""))
                throw new RuntimeException("Por favor informe um único locator [id, xPath or cssSelector] por campo");
    }

    private @Nullable WebElement findBy(@NotNull LocatorType locType, String fieldName) {
        for (Field field : this.getClass().getDeclaredFields()) {

            if (isTheFieldAnnotated(field, fieldName)) {
                final FindBy findBy = field.getAnnotation(FindBy.class);
                if (locType.equals(LocatorType.X_PATH))
                    return gE.findElement(By.xpath(findBy.xPath()));
                if (locType.equals(LocatorType.CSS_SELECTOR))
                    return gE.findElement(By.cssSelector(findBy.cssSelector()));
                if (locType.equals(LocatorType.ID))
                    return gE.findElement(By.id(findBy.id()));
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
