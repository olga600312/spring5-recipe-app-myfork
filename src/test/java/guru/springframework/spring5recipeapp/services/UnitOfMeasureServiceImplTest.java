package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import guru.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.Ingredient;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UnitOfMeasureServiceImplTest {
    @Mock
    private UnitOfMeasureService service;
    private UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
    private UnitOfMeasureCommandToUnitOfMeasure unitOfMeasureCommandToUnitOfMeasure;
    @Before
    public void setUp() throws Exception {
        unitOfMeasureToUnitOfMeasureCommand=new UnitOfMeasureToUnitOfMeasureCommand();
        unitOfMeasureCommandToUnitOfMeasure=new UnitOfMeasureCommandToUnitOfMeasure();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findById() throws Exception {
        //given
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setId(1L);
        when(service.findById(anyLong())).thenReturn(unitOfMeasureToUnitOfMeasureCommand.convert(uom));

        //when
        UnitOfMeasure found=unitOfMeasureCommandToUnitOfMeasure.convert(service.findById(1L));

        //then
        Assert.assertNotNull(found);
        Assert.assertEquals(uom.getId(),found.getId());
        verify(service,times(1)).findById(anyLong());
    }

    @Test
    public void findAll() throws Exception {
        //given
        Set<UnitOfMeasureCommand> givenSet=new HashSet<>();
        UnitOfMeasure uom=new UnitOfMeasure();
        uom.setId(1L);
        givenSet.add(unitOfMeasureToUnitOfMeasureCommand.convert(uom));
        uom=new UnitOfMeasure();
        uom.setId(2L);
        givenSet.add(unitOfMeasureToUnitOfMeasureCommand.convert(uom));
        when(service.findAll()).thenReturn(givenSet);

        //when
        Set<UnitOfMeasureCommand>foundSet=service.findAll();

        //then
        Assert.assertEquals(foundSet.size(),givenSet.size());
        verify(service,times(1)).findAll();
    }

}