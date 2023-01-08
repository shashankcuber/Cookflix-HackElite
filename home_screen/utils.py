from . import views
import ast
from data import *
import pandas as pd
import recommend_sys

def process_dictionary_to_list_of_dict(d):
    recommended_list = []

    dict_0 = {
        "recipe_name" : d["recipe_name"][0],
        "recipe" : d["recipe"][0],
        "ingredients" : d["ingredients"][0],
        "score" : d["score"][0],
        "image" : make_url_https(get_image_url(d["image"][0]).to_list()[0])
    }
    dict_1 = {
        "recipe_name" : d["recipe_name"][1],
        "recipe" : d["recipe"][1],
        "ingredients" : d["ingredients"][1],
        "score" : d["score"][1],
        "image" : make_url_https( get_image_url(d["image"][1]).to_list()[0])
    }
    dict_2 = {
        "recipe_name" : d["recipe_name"][2],
        "recipe" : d["recipe"][2],
        "ingredients" : d["ingredients"][2],
        "score" : d["score"][2],
        "image" : make_url_https(get_image_url(d["image"][2]).to_list()[0])
    }
    dict_3 = {
        "recipe_name" : d["recipe_name"][3],
        "recipe" : d["recipe"][3],
        "ingredients" : d["ingredients"][3],
        "score" : d["score"][3],
        "image" : make_url_https(get_image_url(d["image"][3]).to_list()[0])
    }
    dict_4 = {
        "recipe_name" : d["recipe_name"][4],
        "recipe" : d["recipe"][4],
        "ingredients" : d["ingredients"][4],
        "score" : d["score"][4],
        "image" : make_url_https(get_image_url(d["image"][4]).to_list()[0])
    }
    recommended_list.append(dict_0)
    recommended_list.append(dict_1)
    recommended_list.append(dict_2)
    recommended_list.append(dict_3)
    recommended_list.append(dict_4)

    return recommended_list
    
    
def get_image_url(image):
    df = pd.read_csv("data/url_mapping.csv")
    df_new = df[df['Name'] == image + '.jpg']
    return df_new['URL']

def make_url_https(image_url):
    image_url_n = image_url.replace("http://", "https://")
    return image_url_n

def make_dictionary(title, ingredients, image):
    res = ast.literal_eval(ingredients)
    image_url = get_image_url(image).to_list()
    image_url_n = make_url_https(image_url[0])

    obj = {
            "Title": title,
            "ingredient_list": res,
            "image": image_url_n,
        }
    return obj


def get_required_list(df_sample):
    obj_list = []
    for i in range(len(df_sample)):
        obj = make_dictionary(df_sample.loc[i, "Title"], df_sample.loc[i, "Cleaned_Ingredients"], df_sample.loc[i, "Image_Name"])
        obj_list.append(obj)
    return obj_list

def recommendation_helper(ingredient_list):
    #convert list to string to parse in recommendation system
    ingredient_str = " ".join([str(item) for item in ingredient_list])
    recommendated_df = recommend_sys.rec_sys(ingredient_str)
    req_dict = recommendated_df.to_dict()
    processed_list = process_dictionary_to_list_of_dict(req_dict)
    return processed_list
