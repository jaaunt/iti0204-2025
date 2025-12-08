package ee.taltech.algoritmid.hr;

public class P15 {
    public enum Department {
        CUSTOMER_SUPPORT, RESEARCH_AND_DEVELOPMENT, UNKNOWN;
    }

    private final DisjointSubsets disjointSubsets = new DisjointSubsets();

    public P15() {
        // priit ja Liis on osakondade juhid
        disjointSubsets.addSubset("Priit");
        disjointSubsets.addSubset("Liis");
    }

    public DisjointSubsets getDisjointSubsets() {
        return disjointSubsets;
    }


    // margib, et 2 inimest on omavahel suhelnud
    // kuna inimesed suhtlevad ainult oma osakonna inimestega
    // siis uhendame nad samasse gruppi.
    public void talkedToEachOther(String name1, String name2) {
        // kui mõni neist pole veel lisatud, lisame
        try {
            disjointSubsets.find(name1);
        } catch (IllegalArgumentException e) {
            addPerson(name1);
        }

        try {
            disjointSubsets.find(name2);
        } catch (IllegalArgumentException e) {
            addPerson(name2);
        }

        // uhendame grupid
        disjointSubsets.union(name1, name2);
    }


    // lisab uue inimese susteemi
    public void addPerson(String name) {
        try {
            disjointSubsets.addSubset(name);
        } catch (IllegalArgumentException e) {
            // inimene on juba susteemis, ei tee midagi
        }
    }

    // maarab, et see inimene on CS osakonnast
    // uhendame ta Priiduga (CS marker)
    public void setCustomerSupport(String name) {
        try {
            disjointSubsets.find(name);
        } catch (IllegalArgumentException e) {
            addPerson(name);
        }
        disjointSubsets.union(name, "Priit");
    }

    // maarab, et see inimene on R&D osakonnast
    // uhendame ta Liisiga (R&D marker)
    public void setResearchAndDevelopment(String name) {
        try {
            disjointSubsets.find(name);
        } catch (IllegalArgumentException e) {
            addPerson(name);
        }
        disjointSubsets.union(name, "Liis");
    }

    // tagastab inimese osakonna
    // kontrollib, kas tema juur on sama mis Priidi voi Liisi juur
    public Department getDepartment(String name) {
        try {
            String personRoot = disjointSubsets.find(name);
            String priitRoot = disjointSubsets.find("Priit");
            String liisRoot = disjointSubsets.find("Liis");

            if (personRoot.equals(priitRoot)) {
                return Department.CUSTOMER_SUPPORT;
            }
            if (personRoot.equals(liisRoot)) {
                return Department.RESEARCH_AND_DEVELOPMENT;
            }
            return Department.UNKNOWN;
        } catch (IllegalArgumentException e) {
            // inimene pole susteemis
            return Department.UNKNOWN;
        }
    }
}