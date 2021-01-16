package org.kpn;

import org.junit.jupiter.api.DisplayName;

/* Necessary distribute responsibility between classes so that provide low coupling */
@DisplayName("GRASP: low coupling")
public class n4_LowCoupling {

    public static class CyclicDependenciesA {
        private int a;
        private CyclicDependenciesB cyclicDependenciesB;

        public CyclicDependenciesA(int a) {
            this.a = a;
            this.cyclicDependenciesB = new CyclicDependenciesB(this);
        }
    }

    public static class CyclicDependenciesB {
        private CyclicDependenciesA cyclicDependenciesA;

        public CyclicDependenciesB(CyclicDependenciesA cyclicDependenciesA) {
            this.cyclicDependenciesA = cyclicDependenciesA;
        }
    }
}
