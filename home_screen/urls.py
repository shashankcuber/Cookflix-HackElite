from django.urls import path
from home_screen import views

urlpatterns=[
   path('home/',views.get_food_list,name='home'),
   path('ingredient_list/', views.get_ingredients, name='ingredient'),
   path('recommendar/', views.recommendation, name='recommend'),
]