package util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Paginator<T> {
    private final long size;
    private final int pageSize;
    private final int current;
    private final Stream<T> pageItems;

    private Paginator(long size, int pageSize, int current, Stream<T> source) {
        this.size = size;
        this.pageSize = pageSize;
        this.current = current;
        pageItems = source;
    }

    public static <T> Optional<Paginator> ofStream(Stream<T> source, int pageSize, int current){
        List<T> list = source.collect(Collectors.toList());
        Paginator<T> paginator = new Paginator(list.size(), pageSize, current, list.stream());
        if (pageSize < 1 || !paginator.isValidCurrent()){
            return Optional.empty();
        } else{
            return Optional.of(paginator);
        }
    }

    private boolean isValidCurrent(){
        return current > 0 && current <= count();
    }

    public List<T> getPage(){
        return pageItems.skip(pageSize*(current-1)).limit(pageSize).collect(Collectors.toList());
    }

    public Optional<Integer> next(){
        return current + 1 > count() ? Optional.empty() : Optional.of(current + 1);
    }

    public Optional<Integer> prev(){
        return current - 1 < 1 ? Optional.empty() : Optional.of(current - 1);
    }

    public int count(){
        return (int) (size / pageSize + (size % pageSize == 0 ? 0 : 1));
    }

}
