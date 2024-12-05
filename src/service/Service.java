package service;

import domain.Building;
import repository.IRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Service<T1 extends Building, T2 extends Building> {
    private IRepository<T1> repository1;
    private IRepository<T2> repository2;

    public Service(IRepository<T1> repository1, IRepository<T2> repository2) {
        this.repository1 = repository1;
        this.repository2 = repository2;
    }

    public List<T1> returnAll1() {
        return repository1.getData();
    }

    public List<T2> returnAll2() {
        return repository2.getData();
    }

    public void addEnt1(T1 ent1) throws Exception {
        this.repository1.add(ent1);
    }

    public void addEnt2(T2 ent2) throws Exception {
        this.repository2.add(ent2);
    }

    public void delEnt1(T1 ent1) throws Exception {
        this.repository1.delete(ent1);
    }

    public void delEnt2(T2 ent2) throws Exception {
        this.repository2.delete(ent2);
    }

    public T1 getByIdEnt1(String txt) {
        return this.repository1.getById(txt);
    }

    public T2 getByIdEnt2(String txt) {
        return this.repository2.getById(txt);
    }

    public void updateEnt1(T1 ent1) throws Exception {
        this.repository1.update(ent1);
    }

    public void updateEnt2(T2 ent2) throws Exception {
        this.repository2.update(ent2);
    }

    public void afisareTotalaDupaAn(){
        List<Building> buildingList = new ArrayList<>();
        for(var e:this.repository1.getData()){
            if(e.mustBeRestored()){
                buildingList.add(e);
            }
        }
        for(var e:this.repository2.getData()){
            if(e.mustBeRestored()){
                buildingList.add(e);
            }
        }
        buildingList.sort(Comparator.comparing(Building::getConstructionYear));
        for(var ent:buildingList){
            System.out.println(ent+" Year"+ ent.getConstructionYear()  + " CanBeDestroyed:" + ent.canBeDestoryed() + " CanBeRenovated" + ent.mustBeRestored());
        }
    }

    public void afisareTotalDesc(){
        List<Building> buildingList = new ArrayList<>();
        buildingList.addAll(this.repository1.getData());
        buildingList.addAll(this.repository2.getData());

        buildingList.sort(Comparator.comparing(Building::getConstructionYear).reversed());

        for(var ent:buildingList){
            System.out.println(ent +" Year"+ ent.getConstructionYear() + " CanBeDestroyed:" + ent.canBeDestoryed() + " CanBeRenovated" + ent.mustBeRestored());
        }
    }
}
