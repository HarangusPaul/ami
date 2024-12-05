package domain;

public abstract class Building {
    private Integer constructionYear;

    public Building(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    @Override
    public String toString() {
        return "Building{" +
                "constructionYear=" + constructionYear +
                '}';
    }

    public abstract boolean mustBeRestored();

    public abstract boolean canBeDestoryed();

    public String getForDB(){
        return this.constructionYear + ";";
    }
}
