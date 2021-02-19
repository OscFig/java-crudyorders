package com.lambdaschool.orders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long custcode;

    @Column(nullable = false)
    private String custname;

    private String custcity;
    private String workingarea;
    private String custcountry;
    private String grade;

    // ----------New fields for third day----------
    // Needed for the primitive data types

    //  Used to determine if the field in openingamt has been set or is NULL, meaning 0.0 for a double value
    //  @Transient is used to not get saved to the database.
    @Transient
    public boolean hasvalueforopeningamt = false;

    //  Used for reciveamt
    @Transient
    public boolean hasvalueforreceiveamt = false;

    @Transient
    public boolean hasvalueforpaymentamt = false;

    @Transient
    public boolean hasvalueforoutstandingamt = false;

    //  ------------------------------------------

    private double openingamt;
    private double receiveamt;
    private double paymentamt;
    private double outstandingamt;
    private String phone;

    //  A foreign key to the agent table.
    //  Forms a Many-TO-One relation with the agent table. Many customers to one agent.
    //  Contains an object pointer to the full agent object.
    @ManyToOne
    @JoinColumn(name = "agentcode",
            nullable = false)
    @JsonIgnoreProperties(value = "customers", allowSetters = true)
    private Agent agent;

    //  List of orders associated with this customer. Does not get saved in the database directly.
    //  Forms a One-To-Many relationship to orders. One customer to many orders.
    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "customer",
            allowSetters = true)
    private List<Order> orders = new ArrayList<>();


    //  Two Constructors
    public Customer() {
    }

    public Customer(String custname,
                    String custcity,
                    String workingarea,
                    String custcountry,
                    String grade,
                    double openingamt,
                    double receiveamt,
                    double paymentamt,
                    double outstandingamt,
                    String phone,
                    Agent agent)
    {
        this.custname = custname;
        this.custcity = custcity;
        this.workingarea = workingarea;
        this.custcountry = custcountry;
        this.grade = grade;
        this.openingamt = openingamt;
        this.receiveamt = receiveamt;
        this.paymentamt = paymentamt;
        this.outstandingamt = outstandingamt;
        this.phone = phone;
        this.agent = agent;
    }

    //  Getters and Setters
    public long getCustcode() {
        return custcode;
    }

    public void setCustcode(long custcode) {
        this.custcode = custcode;
    }

    public String getCustname() {
        return custname;
    }

    public void setCustname(String custname) {
        this.custname = custname;
    }

    public String getCustcity() {
        return custcity;
    }

    public void setCustcity(String custcity) {
        this.custcity = custcity;
    }

    public String getWorkingarea() {
        return workingarea;
    }

    public void setWorkingarea(String workingarea) {
        this.workingarea = workingarea;
    }

    public String getCustcountry() {
        return custcountry;
    }

    public void setCustcountry(String custcountry) {
        this.custcountry = custcountry;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public double getOpeningamt() {
        return openingamt;
    }

    public void setOpeningamt(double openingamt) {

        hasvalueforopeningamt = true;
        this.openingamt = openingamt;
    }

    public double getReceiveamt() {
        return receiveamt;
    }

    public void setReceiveamt(double receiveamt) {

        hasvalueforreceiveamt = true;
        this.receiveamt = receiveamt;
    }

    public double getPaymentamt() {
        return paymentamt;
    }

    public void setPaymentamt(double paymentamt) {

        hasvalueforpaymentamt = true;
        this.paymentamt = paymentamt;
    }

    public double getOutstandingamt() {
        return outstandingamt;
    }

    public void setOutstandingamt(double outstandingamt) {

        hasvalueforoutstandingamt = true;
        this.outstandingamt = outstandingamt;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

}
