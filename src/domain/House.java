package domain;

import java.time.LocalDate;

public class House extends Building{
    private boolean isHistorical;

    public House(Integer constructionYear, boolean isHistorical) {
        super(constructionYear);
        this.isHistorical = isHistorical;
    }

    public House(String[] data) {
        super(Integer.parseInt(data[0]));
        this.isHistorical = data[1].equals("false")?false:true;
    }

    public boolean isHistorical() {
        return isHistorical;
    }

    public void setHistorical(boolean historical) {
        isHistorical = historical;
    }

    @Override
    public boolean mustBeRestored() {
        return LocalDate.now().getYear() - this.getConstructionYear() > 100 && isHistorical;
    }

    @Override
    public String toString() {
        return "House{" +
                "isHistorical=" + isHistorical +
                '}';
    }

    @Override
    public boolean canBeDestoryed() {
        return !isHistorical;
    }

    public String getForDB(){
        return this.getConstructionYear() + ";" + (this.isHistorical()?"true":"false") + "\n";
    }
}
