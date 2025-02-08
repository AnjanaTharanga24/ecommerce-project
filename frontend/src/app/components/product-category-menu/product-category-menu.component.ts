import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { ProductService } from '../../services/product.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-category-menu',
  imports: [CommonModule, RouterLink],
  templateUrl: './product-category-menu.component.html',
  styleUrl: './product-category-menu.component.css'
})
export class ProductCategoryMenuComponent implements OnInit {

 productCategories: ProductCategory[] = [];

 constructor(private productService : ProductService){

 }
  ngOnInit(): void {
    this.listProductCategories();
  }

  listProductCategories(){
    this.productService.getProductCategories().subscribe(
      data =>{
        console.log("Product categories received:", data);
        this.productCategories = data;
      },
      error => {
        console.error("Error fetching product categories:", error);
      }
    );
  }
  

}
