package bo;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public enum OperateurBinaire {
    PLUS,
    MOINS,
    FOIS,
    DIVISE;
    private static final List<OperateurBinaire> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    public static OperateurBinaire randomOpperation()  {
        return VALUES.get(new Random().nextInt(SIZE));
    }
}
