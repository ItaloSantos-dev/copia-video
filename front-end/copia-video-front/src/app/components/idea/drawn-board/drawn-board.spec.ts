import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DrawnBoard } from './drawn-board';

describe('DrawnBoard', () => {
  let component: DrawnBoard;
  let fixture: ComponentFixture<DrawnBoard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DrawnBoard]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DrawnBoard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
