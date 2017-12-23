package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.commands.UnitOfMeasureCommand;
import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;

import java.util.List;
import java.util.Set;

public interface UnitOfMeasureService {
    UnitOfMeasureCommand findById(long id);
    Set<UnitOfMeasureCommand> findAll();
}
