import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyKweetComponent } from './my-kweet-component.component';

describe('MyKweetComponent', () => {
  let component: MyKweetComponent;
  let fixture: ComponentFixture<MyKweetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyKweetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyKweetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
