package Domain;

public interface IEntitateFactory<T extends Entitate> {
    T toEntity(String line);
}
