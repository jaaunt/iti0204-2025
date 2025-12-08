package ee.taltech.algoritmid.hr;

import java.util.HashMap;
import java.util.Map;

public class P15 {
    public enum Department {
        CUSTOMER_SUPPORT, RESEARCH_AND_DEVELOPMENT, UNKNOWN;
    }

    private final DisjointSubsets disjointSubsets = new DisjointSubsets();
    // milliste gruppide juurte kohta me teame osakonda
    private Map<String, Department> departmentByRoot = new HashMap<>();

    public P15() {
        // don't remove
    }
    // mis see veel on ^ wtf

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

        // salvestame vanad juurte osakonna info ENNE union'it
        String root1 = disjointSubsets.find(name1);
        String root2 = disjointSubsets.find(name2);

        Department dept1 = departmentByRoot.get(root1);
        Department dept2 = departmentByRoot.get(root2);

        // uhendame grupid
        disjointSubsets.union(name1, name2);

        // leiame uue juure PARAST union'it
        String newRoot = disjointSubsets.find(name1);

        // maarame uuele juurele oige osakonna
        // kui molemal oli osakond maaratud, peaks need olema samad
        if (dept1 != null && dept1 != Department.UNKNOWN) {
            departmentByRoot.put(newRoot, dept1);
        } else if (dept2 != null && dept2 != Department.UNKNOWN) {
            departmentByRoot.put(newRoot, dept2);
        } else {
            // kui kumbki pole teada, jaab UNKNOWN
            departmentByRoot.put(newRoot, Department.UNKNOWN);
        }
    }


    // lisab uue inimese susteemi
    public void addPerson(String name) {
        try {
            disjointSubsets.addSubset(name);
            // alguses ei tea me osakonda
            departmentByRoot.put(name, Department.UNKNOWN);
        } catch (IllegalArgumentException e) {
            // inimene on juba susteemis, ei tee midagi
        }
    }

    // maarab, et see inimene on CS osakonnast
    public void setCustomerSupport(String name) {
        // lisame inimese kui pole juba susteemis
        try {
            disjointSubsets.addSubset(name);
            departmentByRoot.put(name, Department.CUSTOMER_SUPPORT);
        } catch (IllegalArgumentException e) {
            // inimene on juba susteemis, leiame tema juure ja maarame osakonna
            String root = disjointSubsets.find(name);
            departmentByRoot.put(root, Department.CUSTOMER_SUPPORT);
        }
    }

    // maarab, et see inimene on R&D osakonnast
    public void setResearchAndDevelopment(String name) {
        // lisame inimese kui pole juba susteemis
        try {
            disjointSubsets.addSubset(name);
            departmentByRoot.put(name, Department.RESEARCH_AND_DEVELOPMENT);
        } catch (IllegalArgumentException e) {
            // inimene on juba susteemis, leiame tema juure ja maarame osakonna
            String root = disjointSubsets.find(name);
            departmentByRoot.put(root, Department.RESEARCH_AND_DEVELOPMENT);
        }
    }

    // tagastab inimese osakonna
    public Department getDepartment(String name) {
        try {
            String root = disjointSubsets.find(name);
            Department dept = departmentByRoot.get(root);
            return dept != null ? dept : Department.UNKNOWN;
        } catch (IllegalArgumentException e) {
            // inimene pole susteemis
            return Department.UNKNOWN;
        }
    }
}