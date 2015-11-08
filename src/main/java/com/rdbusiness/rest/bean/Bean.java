package com.rdbusiness.rest.bean;

import com.rdbusiness.rest.bean.ext.Audit;

public class Bean {

    private Audit audit;

    public Audit getAudit() {
        if (audit == null) {
            audit = new Audit();
        }

        return audit;
    }

    public void setAudit(Audit audit) {
        this.audit = audit;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "audit=" + audit +
                '}';
    }

}
