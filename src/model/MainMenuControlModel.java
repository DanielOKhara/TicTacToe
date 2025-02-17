package model;

import exception.WrongUsersInputException;

import java.util.Arrays;

public enum MainMenuControlModel {
    PVC(1),
    PVP(2),
    EXIT(0);

    private Integer chosenValue;

    MainMenuControlModel(Integer chosenValue){
        this.chosenValue = chosenValue;
    }

    public static MainMenuControlModel get(String usersInput){
        try {
            Integer chosenNumber = Integer.parseInt(usersInput);
            return Arrays.stream(values())
                    .filter(controlModel -> controlModel.chosenValue.equals(chosenNumber))
                    .findFirst()
                    .orElseThrow(() -> new WrongUsersInputException("Choose from the options offered!"));
        } catch (NumberFormatException ex){
            throw new WrongUsersInputException("Wrong input format!");
        }
    }
}
