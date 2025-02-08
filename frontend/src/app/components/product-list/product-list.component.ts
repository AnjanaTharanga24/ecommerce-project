import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';
import { Product } from '../../common/product';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ActivatedRoute, RouterLink } from '@angular/router';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, HttpClientModule,RouterLink],
  templateUrl: './product-list-grid.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent implements OnInit {
  products: Product[] = [];
  categoryId: number = 1;

  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(() => {
      this.listProducts(this.categoryId);
    });
  }
  
  listProducts(categoryId: number) {
    const hasCategoryId: boolean = this.route.snapshot.paramMap.has('id');
  
    if (hasCategoryId) {
      this.categoryId = +this.route.snapshot.paramMap.get('id')!;
    } else {
      this.categoryId = 1;
    }
  
    this.productService.getProductList(this.categoryId).subscribe(
      data => {
        console.log("API Response:", data); // Debugging
        this.products = data;
      }
    );
  }
}