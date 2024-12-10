package dungeongame.src.test;

import dungeongame.src.model.Skeleton;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SkeletonTest {

    /**
     *
     */
    Skeleton mySkeleton;

    /**
     *
     */
    @BeforeEach
    public void setup() {
        mySkeleton = new Skeleton(100, 10, 20, 5, 3, 0.5, "TestSkeleton");
    }
}
