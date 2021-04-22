package Service;

import java.util.ArrayList;

public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(int id,T t);
    public ArrayList<T> selectAll();
    public T selectOne(int id);
    public boolean existId(int id);
    public  int  lastIdAdded();
}
