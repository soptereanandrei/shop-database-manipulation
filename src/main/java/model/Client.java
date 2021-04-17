package model;

public class Client {
    private String name;
    private String address;
    private String email;
    private int age;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public Client(String name, String address, String email, int age)
    {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    public String toString()
    {
        return "name=" + name + " address=" + address + " email=" + email + " age=" + age;
    }
}
