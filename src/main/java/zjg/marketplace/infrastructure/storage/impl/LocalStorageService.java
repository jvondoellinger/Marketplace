package zjg.marketplace.infrastructure.storage.impl;

//@Service
public class LocalStorageService /*implements StorageService*/ {
/*    private static final Path path = Paths.get("uploads");
    @Override
    public Mono<Void> upload(byte[] file, String fileName) {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
            var oldName = Objects.requireNonNull(fileName);
            var newName = PathUtils.modifyName(oldName, fileName);
            var out = path.resolve(newName);

            ByteBuffer buffer = file.asByteBuffer().asReadOnlyBuffer();
            InputStream inputStream = new ByteBufferBackedInputStream(buffer);
            Files.copy(inputStream, out, StandardCopyOption.REPLACE_EXISTING);

            return out.normalize().toString()   ;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar arquivo", e);
        } finally {
            DataBufferUtils.release(dataBuffer); // Liberando o buffer
        }
    }

    @Override
    public Mono<Void> remove(Resource file) {
        return null;
    }

    @BadCode
    @Override
    public Mono<byte[]> get(String name) {
        try{
            Resource resource = new FileSystemResource(path.resolve(name));
            return Mono.just(resource.getContentAsByteArray());
        } catch (Exception e) {
            return Mono.error(new RuntimeException("Arquivo não encontrado ou não acessível"));
        }
    }*/
}
