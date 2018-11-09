package pcs.ub.edu.ar.clinicavirtual.enums;

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
    DEACTIVATE{
        public String toString(){
            return "/deactivate";
        }
    },
    CLINIC{
        public String toString(){
            return "/clinic";
        }
    },
    SEARCH{
        public String toString(){
            return "/search";
        }
    },
    HCP{
        public String toString(){
            return "/hcp";
        }
    },
    PATIENT{
        public String toString(){
            return "/patient";
        }
    },
    SPECIALITY{
        public String toString(){
            return "/speciality";
        }
    },
    SPECIALITY_BY_ID {
        public String toString(){
            return "/speciality/";
        }
    },
    SPECIALITIES{
        public String toString(){
            return "/specialities";
        }
    },
    APPOINTMENTS{
        public String toString(){
            return "/appointments";
        }
    },
    HCPSPECIALITIES{
        public String toString(){
            return "/hcpspecialities";
        }
    },
    SCHEDULE{
        public String toString(){
            return "/schedule";
        }
    },
    LOGIN_9{
        public String toString(){
            return "/login9";
        }
    }

}
