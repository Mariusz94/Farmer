import java.util.List;

public class Barn {
    private String name;
    private int id;
    private List<Animal> list;

    public Barn(String name, int id, List list) {
        this.name = name;
        this.id = id;
        this.list = list;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
