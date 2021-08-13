package br.com.zupacademy.iagofaria.mercadolivre.produto;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

public class ImagemProdutoRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

    public ImagemProdutoRequest(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProdutoRequest that = (ImagemProdutoRequest) o;
        return Objects.equals(imagens, that.imagens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imagens);
    }
}
