package pcs.ub.edu.ar.clinicavirtual.enums;

public enum USER_TYPE {
    NEW_USER(0),
    PATIENT(1),
    HCP(2),
    CLINIC(3);
    private final Integer value;

    USER_TYPE(final Integer newValue) {
        value = newValue;
    }

    public Integer getValue() { return value; }
}
