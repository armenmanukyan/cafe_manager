package com.myCafe.core.dto;

import java.util.Objects;

public class CafeTable {
    private Integer Id;

    private CafeUser user;

    private Integer number;

    public Integer  getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public CafeUser getUser() {
        return user;
    }

    public void setUser(CafeUser user) {
        this.user = user;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeTable table = (CafeTable) o;
        return Objects.equals(Id, table.Id) &&
                Objects.equals(user, table.user) &&
                Objects.equals(number, table.number);
    }

    @Override
    public int hashCode() {

        return Objects.hash(Id, user, number);
    }
}
