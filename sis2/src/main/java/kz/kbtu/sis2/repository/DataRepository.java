package kz.kbtu.sis2.repository;


import org.springframework.stereotype.Repository;

@Repository
public class DataRepository {
    public String getSource(){
        return "dummyjson";
    }
}
