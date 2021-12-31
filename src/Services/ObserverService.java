package Services;

/**
 * This class defines Observer Service
 * @author Samin Mahdipour
 * @version 3.0
 * @since 12.7.2021
 * */
public interface ObserverService {
    /**
     * @param jData data
     * @return reflex
     */
    public int begin(String jData);

    /**
     *
     * @param next next
     */
    public void changeNext(int next);
}
