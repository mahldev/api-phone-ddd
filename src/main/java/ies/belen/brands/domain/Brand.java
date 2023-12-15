package ies.belen.brands.domain;

public class Brand {

    private final BrandId id;
    private final BrandName name;

    public Brand(String id, String name) {
        this.id = new BrandId(id);
        this.name = new BrandName(name);
    }

    public String id() {
        return id.value();
    }

    public String name() {
        return name.value();
    }
}
