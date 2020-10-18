package ru.ssu.solution.services;

import ru.ssu.solution.entities.Function;

public interface FunctionBuilderService {
    Function getFunctionFromTextFormatGraph(String graphDescription);
}
