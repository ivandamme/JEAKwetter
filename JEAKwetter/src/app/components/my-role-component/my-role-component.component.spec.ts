import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MyRolComponent } from './my-rol-component.component';

describe('MyRolComponent', () => {
  let component: MyRolComponent;
  let fixture: ComponentFixture<MyRolComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MyRolComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MyRolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
