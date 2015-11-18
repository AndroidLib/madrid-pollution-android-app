package entities;

public class PollutantInfo {

    private String mName;
    private String mDescription;
    private String mEffects;


    public PollutantInfo(String name, String description, String effects) {
        mName = name;
        mDescription = description;
        mEffects = effects;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getEffects() {
        return mEffects;
    }

    public void setEffects(String effects) {
        mEffects = effects;
    }
}
