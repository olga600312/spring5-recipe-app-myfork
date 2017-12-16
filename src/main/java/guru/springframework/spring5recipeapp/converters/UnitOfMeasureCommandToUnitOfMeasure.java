package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

   // @Synchronized  It's not needed. We are always returning new object and no class level variables have been changed for that converter.
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {
        final UnitOfMeasure uom;
        if (source != null) {
            uom = new UnitOfMeasure();
            uom.setId(source.getId());
            uom.setDescription(source.getDescription());
        }else
            uom=null;
        return uom;
    }
}
