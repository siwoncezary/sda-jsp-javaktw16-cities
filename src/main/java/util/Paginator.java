package util;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Paginator {
    private final long sizeCollection;
    private final long pageSize;
    private final long currentPage;

    private Paginator(long sizeCollection, long current, long sizePage) {
        this.sizeCollection = sizeCollection;
        this.pageSize = sizePage;
        this.currentPage = current;
    }

    public static Optional<Paginator> of(long size, long page, long pageSize){
        if (isValid(size, page, pageSize)) {
            return Optional.of(new Paginator(size, page, pageSize));
        }
        return Optional.empty();
    }

    public long getPageSize() {
        return pageSize;
    }

    public long last(){
        return count();
    }

    public long current(){
        return currentPage;
    }

    public Optional<Long> next(){
        return currentPage + 1 <= count() ? Optional.ofNullable(currentPage + 1) : Optional.empty();
    }

    public Optional<Long> prev(){
        return currentPage -1 > 0 ? Optional.ofNullable(currentPage - 1) : Optional.empty();
    }

    public long count(){
        return sizeCollection / pageSize + (sizeCollection % pageSize == 0 ? 0 : 1);
    }

    public <T> List<T> getPaged(@NotNull Stream<T> stream){
        long page  = Long.max(1, currentPage);
        page = Long.min(page, count());
        return stream.skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
    }

    private static long clip(long size, long page, long pageSize){
        return Long.min(Long.max(1, page) , size / pageSize + (size % pageSize == 0 ? 0 : 1));
    }

    private static boolean isValid(long size, long page, long pageSize){
        return clip(size, page, pageSize) == page;
    }
}