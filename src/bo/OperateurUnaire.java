package bo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum OperateurUnaire {
        RACINE,
        INVERSE;

        private static final List<OperateurUnaire> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
        private static final int SIZE = VALUES.size();


        public static OperateurUnaire randomOpperation()  {
            return VALUES.get(new Random().nextInt(SIZE));
        }

}
