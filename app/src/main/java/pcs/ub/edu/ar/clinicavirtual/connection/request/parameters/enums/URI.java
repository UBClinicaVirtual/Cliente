package pcs.ub.edu.ar.clinicavirtual.connection.request.parameters.enums;

public enum URI {

    REGISTER{
        public String toString(){
            return "/register";
        }
    },
    LOGIN{
        public String toString(){
            return "/login";
        }
    },
    USER{
        public String toString(){
            return "/user";
        }
    },
    LOGOUT{
        public String toString(){
            return "/logout";
        }
    },
    USER_CLINIC{
        public String toString(){
            return "/user/clinic";
        }
    },
    CLINIC_SEARCH{
        public String toString(){
            return "/clinic/search";
        }
    },
    USER_HCP{
        public String toString(){
            return "/user/hcp";
        }
    },
    HCP_SEARCH{
        public String toString(){
            return "/hcp/search";
        }
    },
    USER_PATIENT{
        public String toString(){
            return "/user/patient";
        }
    },
    PATIENT_SEARCH{
        public String toString(){
            return "/patient/search";
        }
    },
    SPECIALITY{
        public String toString(){
            return "/speciality";
        }
    },
    SPECIALITY_ID_SPECIALITY{
        public String toString(){
            return "/speciality/{id_speciality}";
        }
    },
    SPECIALITIES{
        public String toString(){
            return "/specialities";
        }
    },
    USER_PATIENT_APPOINTMENTS{
        public String toString(){
            return "/user/patient/appointments";
        }
    },
    USER_HCP_APPOINTMENTS{
        public String toString(){
            return "/user/hcp/appointments";
        }
    },
    USER_CLINIC_APPOINTMENTS{
        public String toString(){
            return "/user/clinic/appointments";
        }
    },
    USER_CLINIC_HCPSPECIALITIES{
        public String toString(){
            return "/user/clinic/hcpspecialities";
        }
    },
    USER_CLINIC_HCPSPECIALITIES_SEARCH{
        public String toString(){
            return "/user/clinic/hcpspecialities/search";
        }
    }

}
