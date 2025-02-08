import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Product } from '../common/product';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
 

  private baseUrl = 'http://localhost:8080/api/products';

  private categoryUrl = 'http://localhost:8080/api/products/categories';

  constructor(private httpClient: HttpClient) { }

  getProductList(categoryId: number): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/${categoryId}`;
    return this.httpClient.get<{ content: Product[] }>(searchUrl).pipe(
      map(response => response.content || []) 
    );
  }

  getProductCategories(): Observable<ProductCategory[]> {
    return this.httpClient.get<ProductCategory[]>(this.categoryUrl);
  }

  searchProducts(theKeyword: string) {
    const searchUrl = `${this.baseUrl}/names/${theKeyword}`;
    return this.httpClient.get<{ content: Product[] }>(searchUrl).pipe(
      map(response => response.content || []) 
    );
  }
  

}

interface GetResponse {
  _embedded: {
    products: Product[];
  }
}
