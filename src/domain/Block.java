package domain;

import java.time.LocalDate;

public class Block extends Building{
    private Integer totalApartaments;
    private Integer ocupiedApartaments;

    public Block(Integer constructionYear, Integer totalApartaments, Integer ocupiedApartaments) {
        super(constructionYear);
        this.totalApartaments = totalApartaments;
        this.ocupiedApartaments = ocupiedApartaments;
    }

    public Block(String[] data) {
        super(Integer.parseInt(data[0]));
        this.totalApartaments = Integer.parseInt(data[1]);
        this.ocupiedApartaments = Integer.parseInt(data[2]);
    }

    public Integer getTotalApartaments() {
        return totalApartaments;
    }

    public void setTotalApartaments(Integer totalApartaments) {
        this.totalApartaments = totalApartaments;
    }

    public Integer getOcupiedApartaments() {
        return ocupiedApartaments;
    }

    public void setOcupiedApartaments(Integer ocupiedApartaments) {
        this.ocupiedApartaments = ocupiedApartaments;
    }

    @Override
    public boolean mustBeRestored() {
        return LocalDate.now().getYear() - this.getConstructionYear() > 40 && 80*this.totalApartaments/100 < this.ocupiedApartaments;
    }

    @Override
    public boolean canBeDestoryed() {
        return 5*this.totalApartaments/100 > this.ocupiedApartaments;
    }

    @Override
    public String toString() {
        return "Block{" +
                "totalApartaments=" + totalApartaments +
                ", ocupiedApartaments=" + ocupiedApartaments +
                '}';
    }

    public String getForDB(){
        return this.getConstructionYear() + ";" + this.getTotalApartaments() + ";" + this.getOcupiedApartaments() + "\n";
    }
}
