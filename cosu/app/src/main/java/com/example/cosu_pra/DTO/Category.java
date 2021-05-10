package com.example.cosu_pra.DTO;

import java.util.HashMap;
import java.util.Map;

public class Category {
    public static final String DEVELOPER = "developer";
    public static final String DESIGNER = "designer";

    private boolean developer;
    private boolean designer;

    public Category(){}

    public Category(boolean isDeveloper, boolean isDesigner){
        developer = isDeveloper;
        designer = isDesigner;
    }

    public boolean getDeveloper(){
        return developer;
    }

    public boolean getDesigner(){
        return designer;
    }
}
