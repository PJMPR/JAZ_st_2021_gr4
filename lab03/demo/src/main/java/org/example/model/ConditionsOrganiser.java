package org.example.model;

import org.example.conditions.Conditions;
import org.example.conditions.GenderCondition;
import org.example.conditions.PageCondition;
import org.example.conditions.age.AgeFromCondition;
import org.example.conditions.age.AgeToCondition;
import org.example.conditions.income.IncomeFromCondition;
import org.example.conditions.income.IncomeToCondition;
import org.example.conditions.name.NameOfPersonCondition;
import org.example.conditions.name.SurnameOfPersonCondition;

import java.util.List;

public class ConditionsOrganiser {
    private List<Conditions> listOfConditions;

    public ConditionsOrganiser() {
        this.listOfConditions = List.of(
                new AgeFromCondition(),
                new AgeToCondition(),
                new GenderCondition(),
                new IncomeToCondition(),
                new IncomeFromCondition(),
                new NameOfPersonCondition(),
                new SurnameOfPersonCondition(),
                new PageCondition()
        );
    }

    public List<Conditions> getListOfConditions() {
        return listOfConditions;
    }
}
