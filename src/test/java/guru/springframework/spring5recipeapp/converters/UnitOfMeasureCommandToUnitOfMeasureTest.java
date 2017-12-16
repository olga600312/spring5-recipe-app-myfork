package guru.springframework.spring5recipeapp.converters;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {
    private static final String DESCRIPTION="description";
    private static final Long ID=1L;
    private UnitOfMeasureCommandToUnitOfMeasure converter;


    @Before
    public void setUp() throws Exception {
        converter=new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testNullParameter() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyParameter() throws Exception {
        assertNotNull(converter.convert(new UnitOfMeasureCommand()));
    }


    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasureCommand command=new UnitOfMeasureCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);

        //when
        UnitOfMeasure uom=converter.convert(command);

        //then
        assertNotNull(uom);
        assertEquals(ID,uom.getId());
        assertEquals(DESCRIPTION,uom.getDescription());
    }

}