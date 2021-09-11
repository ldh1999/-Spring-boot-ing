package com.ldh.BeanFactor.BeanFactorImpl;
import java.util.LinkedList;
import java.util.List;

public class BeanListAll{

    private List<Class> listClassAll = new LinkedList<>();

    private static BeanListAll beanListAll = new BeanListAll();

    public static BeanListAll getBeanListAll(){
        return beanListAll;
    }

    private BeanListAll() {
    }

    public void setListClassAll(List<Class> listClassAll) {
        this.listClassAll = listClassAll;
    }

    public void getClass_(){
        listClassAll.forEach((e)->{
            System.out.println(e.getName());
        });
    }

    public List<Class> getListClassAll() {
        return listClassAll;
    }
}
