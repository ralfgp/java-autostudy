package autostudy.section2ClassesAndObjects;

public enum Condition{
    HOT("Warning HOT"),
    WARM("Just right"),
    COLD("Warning COLD");
    private String caution;
    // An enum with variables must have a constructor and this constructor must be of default or private access
    private Condition(String caution){
        this.caution = caution;
    }
    public String getCaution(){
        return caution;
    }
}
