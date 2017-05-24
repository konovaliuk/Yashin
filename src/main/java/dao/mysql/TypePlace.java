package dao.mysql;

public enum TypePlace {
    BERTH{
        @Override
        public String toString() {
            return "B";
        }
    },
    COMPARTMENT{
        @Override
        public String toString() {
            return "C";
        }
    },
    DELUXE{
        @Override
        public String toString() {
            return "L";
        }
    }
}
