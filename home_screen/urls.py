from django.urls import path
from home_screen import views

urlpatterns=[
   path('home/',views.get_food_list,name='home'),
   path('ingrident_list/', views.get_ingrideints, name='ingredient')
]