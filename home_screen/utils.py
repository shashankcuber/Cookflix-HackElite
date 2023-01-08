from . import views
import ast
from data import *
import pandas as pd

def get_image_url(image):
    df = pd.read_csv("data/url_mapping.csv")
    df_new = df[df['Name'] == image + '.jpg']
    return df_new['URL']

def make_dictionary(title, ingredients, image):
    res = ast.literal_eval(ingredients)
    image_url = get_image_url(image).to_list()
    image_url_n = image_url[0].replace("http://", "https://")
    obj = {
            "Title": title,
            "ingredient_list": res,
            "image": image_url_n,
        }
    return obj


def get_required_list(df_sample):
    obj_list = []
    for i in range(len(df_sample)):
        # print(df_sample.loc[i, "Title"])
        obj = make_dictionary(df_sample.loc[i, "Title"], df_sample.loc[i, "Cleaned_Ingredients"], df_sample.loc[i, "Image_Name"])
        obj_list.append(obj)
    
    return obj_list

