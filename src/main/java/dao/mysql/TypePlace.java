package dao.mysql;

public enum TypePlace {
    BERTH{
        @Override
        public String toString() {
            return "BERTH";
        }
    },
    COMPARTMENT{
        @Override
        public String toString() {
            return "COMPARTMENT";
        }
    },
    DELUXE{
        @Override
        public String toString() {
            return "DELUXE";
        }
    }
}
